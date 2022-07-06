# Create file with 5-letter words only

output = open('lowercase_five_letter_words.txt', 'w')

with open('lowercase_words.txt', 'r') as f:
    for i in f:
        if len(i) == 6:  # new line is +1 char
            output.write(i)
