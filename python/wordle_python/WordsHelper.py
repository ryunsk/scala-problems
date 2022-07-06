import random


class WordsHelper:
    def __init__(self, file):
        self.file = file

    def words_to_list(self):
        output = open(self.file, 'r')
        output_list = output.read().splitlines()
        return output_list

    def generate_random_word(self):
        random_index = random.randint(0, len(self.words_to_list()) - 1)
        return self.words_to_list()[random_index]
