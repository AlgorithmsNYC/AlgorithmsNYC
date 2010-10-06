
class Board(object):
    def __init__(self, board):
        self.board = board
        
    def is_empty(self, position):
        if self.board[position] == 0:
            return True
        else:
            return False
        
    def belongs_to(self, position, player):
        if self.board[position] == player:
            return True
        else:
            return False
    
    def possible_moves(self):
        empty_spots = []
        for position in range(9):
            if self.board[position] == 0:
                empty_spots.append(position)
        return empty_spots

    def set_position(self, position, player):
        self.board[position] = player

    def print_position(self, position):
        if self.board[position] == 0:
            return " "
        elif self.board[position] == 1:
            return "X"
	else:
	    return "O"
    
    def not_enemy(self, position, player):
        if self.board[position] == 0 or self.board[position] == player:
            return True
        return False

    def print_board(self):
        print " " + self.print_position(0) + " | " + self.print_position(1) + " | " + self.print_position(2) + " "  
        print "-----------"
        print " " + self.print_position(3) + " | " + self.print_position(4) + " | " + self.print_position(5) + " " 
        print "-----------"
        print " " + self.print_position(6) + " | " + self.print_position(7) + " | " + self.print_position(8) + " " 

    def print_selection(self):
        print "\n 0 | 1 | 2 "  
        print "-----------"
        print " 3 | 4 | 5 " 
        print "-----------"
        print " 6 | 7 | 8 "
	print "***********" 

    def terminal_check(self, player):
        if self.belongs_to(0, player) and self.belongs_to(4, player) and self.belongs_to(8, player):
            return True
        if self.belongs_to(2, player) and self.belongs_to(4, player) and self.belongs_to(6, player):
            return True
        if self.belongs_to(0, player) and self.belongs_to(1, player) and self.belongs_to(2, player):
            return True
        if self.belongs_to(3, player) and self.belongs_to(4, player) and self.belongs_to(5, player):
            return True
        if self.belongs_to(6, player) and self.belongs_to(7, player) and self.belongs_to(8, player):
            return True
        if self.belongs_to(0, player) and self.belongs_to(3, player) and self.belongs_to(6, player):
            return True
        if self.belongs_to(1, player) and self.belongs_to(4, player) and self.belongs_to(7, player):
            return True
        if self.belongs_to(2, player) and self.belongs_to(5, player) and self.belongs_to(8, player):
            return True
        return False


    def evaluate_line(self, position1, position2, position3, player):
        if self.not_enemy(position1, player) and self.not_enemy(position2, player) and self.not_enemy(position3, player):
            return True
        return False

    def evaluate_board(self, player):
        score = 0
        if self.evaluate_line(0, 1, 2, player):
            score +=1
        if self.evaluate_line(3, 4, 5, player):
            score +=1
        if self.evaluate_line(6, 7, 8, player):
            score +=1
        if self.evaluate_line(0, 3, 6, player):
            score +=1
        if self.evaluate_line(1, 4, 7, player):
            score +=1
        if self.evaluate_line(2, 5, 8, player):
            score +=1
        if self.evaluate_line(0, 4, 8, player):
            score +=1
        if self.evaluate_line(2, 4, 6, player):
            score +=1
        return score

            
    def minimax(self, player1, player2, depth):
        if self.board[4] == 0:
            return 4
        best_move = -1
        max_points = -15
#        print self.possible_moves()
        for move in self.possible_moves():
            possible_board = self.board[:]
            possible_state = Board(possible_board)
            possible_state.set_position(move, player1)
            move_score = possible_state.min(player2, player1, depth-1)
            if move_score > max_points:
                max_points = move_score
                best_move = move
 #       print best_move
        return best_move


    def min(self, player1, player2, depth):
        if self.terminal_check(player1):
            return -10
        elif self.terminal_check(player2):
            return 10
        if depth > 0:
            if len(self.possible_moves()) > 0:
                min_points = 15
                for move in self.possible_moves():
                    possible_board = self.board[:]
                    possible_state = Board(possible_board)
                    possible_state.set_position(move, player1)
                    move_score = possible_state.max(player2, player1, depth-1)
                    if move_score < min_points:
                        min_points = move_score
                return min_points
            else:
                return self.evaluate_board(player1) - self.evaluate_board(player2)
        else:
            return self.evaluate_board(player1) - self.evaluate_board(player2)


    def max(self, player1, player2, depth):
        if self.terminal_check(player1):
            return 10
        elif self.terminal_check(player2):
            return -10
        if depth > 0:
            if len(self.possible_moves()) > 0:
                max_points = -15
                for move in self.possible_moves():
                    possible_board = self.board[:]
                    possible_state = Board(possible_board)
                    possible_state.set_position(move, player1) 
                    move_score = possible_state.min(player2, player1, depth-1)
                    if move_score > max_points:
                        max_points = move_score
                return max_points
            else:
                return self.evaluate_board(player1) - self.evaluate_board(player2)
        else:
            return self.evaluate_board(player1) - self.evaluate_board(player2)    

def cli_player1():
    while len(game.possible_moves()) != 0:
	#game.print_board()
	print "\n============"        
	game.print_selection()
        move = int(raw_input("Enter move: "))
        game.set_position(move, player1)
        if game.terminal_check(player1):
            print "Player X wins."
	    break
        game.set_position(game.minimax(player2, player1, 3), player2)
        print "\n============"
	print "Computer moves:\n"        
	game.print_board()
        if game.terminal_check(player2):
            print "Player O wins."
	    break


def cli_player2():
    while len(game.possible_moves()) != 0:
        game.print_selection()
        game.set_position(game.minimax(player2, player1, 3), player2)
        print " "
        game.print_board()
        if game.terminal_check(player2):
	    break
        move = int(raw_input("Enter move: "))
        game.set_position(move, player1)
        if game.terminal_check(player1):
	    break


player1 = 1
player2 = 2

initial_board = [0,0,0,0,0,0,0,0,0]
game = Board(initial_board)


print "*" * 40
print "*" * 40
print "\nWelcome to Tic Tac Toe! I assume you know how to play."
print "Please select a player: "
print "\tSelect '1' for X"
print "\tSelect '2' for O"
selection = raw_input("> ")

if int(selection) == player1:
    print "You have selected X"
    game.print_board()
    cli_player1()
elif int(selection) == player2:
    print "You have selected O"
    game.print_board()
    cli_player2()
else:
    print "invalid selection"
    exit()

    
print "End of Game"
