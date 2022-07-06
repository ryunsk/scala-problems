import unittest

from WordleGame import WordsHelper, WordleGame


class TestWords(unittest.TestCase):
    words = WordsHelper('test_five_letter_words.txt').words_to_list()

    def test_file_to_words(self):
        self.assertEqual(['above', 'blade', 'caves', 'fling', 'latin', 'panel'], self.words)


class Test(unittest.TestCase):
    game = WordleGame(['hello', 'caves', 'evade', 'blade'], 'evade')

    def test_compare_two_words_happy_path(self):
        self.assertEqual([['e', 'green'], ['v', 'green'], ['a', 'green'], ['d', 'green'], ['e', 'green']],
                         self.game.compare_words('evade'))

    def test_compare_two_words(self):
        self.assertEqual([['c', 'grey'], ['a', 'yellow'], ['v', 'yellow'], ['e', 'yellow'], ['s', 'grey']],
                         self.game.compare_words('caves'))

    def test_is_valid_true(self):
        self.assertEqual(True, self.game.is_word_valid('evade'))

    def test_is_valid_false(self):
        self.assertEqual(False, self.game.is_word_valid('aaaaa'))
