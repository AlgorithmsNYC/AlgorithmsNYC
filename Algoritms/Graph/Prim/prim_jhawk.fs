open System

let adjacency_matrix = 
    [ [0;1;4;0;0];
      [1;0;2;6;0];
      [4;2;0;3;1];
      [0;6;3;0;0];
      [0;0;1;0;0] ]

[<CustomEquality; CustomComparison>]
type Edge = {
        _start:  int
        _end:    int
        _weight: int
    }   with    override this.ToString() =
                    this._start.ToString() + " to " + this._end.ToString() + " with weight " + this._weight.ToString()
                override this.GetHashCode () = hash this._end
                override this.Equals other = (other :?> Edge)._end = this._end
                interface IComparable with
                    member this.CompareTo other = 
                        let other' = (other :?> Edge)
                        match other'._end, this._end with
                            | a, b when a < b ->  1
                            | a, b when a > b -> -1
                            | _               ->  0

// extend List - fold with index
module List =
    let foldi (f: int -> 'a -> 'b -> 'a) (acc: 'a) (l: 'b list): 'a =
        let rec foldi' idx (f: int -> 'a -> 'b -> 'a) (acc: 'a) (l: 'b list) = 
            match l with 
                | []    -> acc
                | x::xs -> foldi' (idx + 1) f (f idx acc x) xs
        foldi' 0 f acc l

let update this_vertex other_vertex (acc: Map<Edge,int>) w = 
    if this_vertex < other_vertex then
        match w with 
            | 0 ->  acc 
            | w ->  let first, second = if this_vertex < other_vertex then (this_vertex, other_vertex) else (other_vertex, this_vertex) 
                    let edge = { _start = first ; _end = second ; _weight = w }
                    if acc.ContainsKey(edge) && acc.[edge] < w then acc 
                    else acc.Add(edge, w)
    else acc

let prim_mst (am: int list list): Map<Edge,int> =
    let rec prim_mst' (idx: int) (acc: Map<Edge,int>) (am: int list list) =     
        match am with 
            | []    -> acc
            | v::vs -> let acc = v |> List.foldi (update idx) acc
                       prim_mst' (idx + 1) acc vs
    prim_mst' 0 Map.empty am

adjacency_matrix |> prim_mst |> Map.iter (fun e _ -> Console.WriteLine(e.ToString()))
let _ = Console.ReadLine()