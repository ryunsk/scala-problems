from WordsHelper import WordsHelper


class WordleGame:
    def __init__(self, list_of_words, goal):
        self.list_of_words = list_of_words
        self.goal = goal

    def is_word_valid(self, guess):
        return True if guess in self.list_of_words and guess.islower() and len(guess) == 5 else False

    # Compare answer with user's guessed word
    def compare_words(self, guess):
        result = []
        for i in range(5):
            if guess[i] in self.goal:
                if guess[i] == self.goal[i]:
                    result.append([guess[i], 'green'])
                else:
                    result.append([guess[i], 'yellow'])

            else:
                result.append([guess[i], 'grey'])
        return result

    # Check all the letters are green
    def is_guess_correct(self, result):
        for i in result:
            if i[1] == 'green':
                pass
            else:
                return False
        return True

    def play(self):
        tries = 1
        while tries < 7:
            user_input = input('Guess the word: ')
            print('Number of tries: ' + str(tries))
            if self.is_word_valid(user_input):
                result = self.compare_words(user_input)
                print(result)
                print('====================')
                if self.is_guess_correct(result):
                    print("You won!")
                    return
                else:
                    tries += 1
            else:
                print('Word must be valid, 5 letters long, and must be in lowercase')
                print('====================')
        print('The answer was ' + self.goal + '...')
        return


if __name__ == '__main__':
    words = WordsHelper('lowercase_five_letter_words.txt')
    game = WordleGame(words.words_to_list(), words.generate_random_word())
    game.play()
