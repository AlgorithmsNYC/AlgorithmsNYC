def encode(message):
	letters = [chr(x) for x in range(ord('a'), ord('z')+1)]

	cyphermap = {}
	encoded = ""
	
	for letter in message:
		if not cyphermap.get(letter):
			cyphermap[letter] = letters.pop(0)

		encoded += cyphermap[letter]

	print message, " -> ", encoded

encode('hello')
encode('abcd')
encode('topcoder')
encode('encryption')
encode('thequickbrownfoxjumpsoverthelazydog')
