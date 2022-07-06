import unittest

from world_cup_prognostication import Simulation


class TestSimulation(unittest.TestCase):
    simulation = Simulation()

    def test_group_advance_no_ties(self):
        my_win_counter = {"England": 3, "Norway": 2, "Austria": 1, "Northern Ireland": 0}
        self.assertEqual(["England", "Norway"],
                         self.simulation.find_countries_to_advance_in_group(my_win_counter))

    def test_group_advance_ties_2(self):
        my_win_counter = {"England": 3, "Norway": 1, "Austria": 1, "Northern Ireland": 1}
        self.assertIn(self.simulation.find_countries_to_advance_in_group(my_win_counter),
                      [["England", "Norway"], ["England", "Austria"], ["England", "Northern Ireland"]])

    def test_group_advance_ties_3(self):
        my_win_counter = {"England": 2, "Norway": 2, "Austria": 1, "Northern Ireland": 1}
        self.assertIn(self.simulation.find_countries_to_advance_in_group(my_win_counter),
                      [["England", "Norway"], ["Norway", "England"]])
