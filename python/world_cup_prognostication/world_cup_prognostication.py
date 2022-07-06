import itertools
import random
from typing import List


class Simulation:
    team_ratings = {"England": 1692,
                    "Germany": 1775,
                    "Netherlands": 1726,
                    "Denmark": 1503,
                    "Norway": 1537,
                    "Sweden": 1801,
                    "France": 1832,
                    "Belgium": 1374,
                    "Iceland": 1476,
                    "Spain": 1668,
                    "Finland": 1210,
                    "Austria": 1380,
                    "Italy": 1582,
                    "Switzerland": 1327,
                    "Northern Ireland": 980,
                    "Portugal": 1248}

    # In order of group A, B, C and D.
    brackets = [["England", "Austria", "Norway", "Northern Ireland"],
                ["Germany", "Denmark", "Spain", "Finland"],
                ["Netherlands", "Sweden", "Switzerland", "Portugal"],
                ["France", "Italy", "Belgium", "Iceland"]]

    def find_group_stage_winners(self, teams: List[str]) -> List[str]:
        """
        Finds two teams in a group who will advance to the knockout stage.
        For simplicity there are no draws and tiebreakers.
        In case of same points e.g. 3, 1, 1, 1 or 2, 2, 1, 1 or 2, 2, 2, 0
        Other cases without ties: 3, 2, 1, 0
        :param teams: List of teams in the group stage
        :return: A list with two teams
        """
        win_counter = dict.fromkeys(teams, 0)
        list_of_matches = list(itertools.combinations(teams, 2))

        for team_a, team_b in list_of_matches:
            if self.is_a_winner(team_a, team_b):
                win_counter[team_a] += 1
            else:
                win_counter[team_b] += 1

        return self.find_countries_to_advance_in_group(win_counter)

    def find_countries_to_advance_in_group(self, win_counter):
        print("Win counter " + str(win_counter))
        winners = sorted(win_counter.items(), key=lambda x: (-x[1], random.random()))

        return list(map(lambda x: x[0], winners[:2]))

    def probability_of_win_a(self, a_rating: int, b_rating: int) -> float:
        """
        :param a_rating: ELO rating of team A
        :param b_rating: ELO rating of team B
        :return: Probability of team A winning
        """
        m = (b_rating - a_rating) / 400
        return 1 / (1 + 10 ** m)

    def is_a_winner(self, team_a: str, team_b: str) -> bool:
        """
        Plays a game between two team and checks if team A is the winner.
        :param team_a:
        :param team_b:
        :return: Boolean to check if team A won. False means team B won.
        """
        random_probability = random.random()
        return random_probability <= self.probability_of_win_a(self.team_ratings[team_a], self.team_ratings[team_b])


simulation = Simulation()
groupA = ["England", "Austria", "Norway", "Northern Ireland"]

# for i in range(10):
#     print(simulation.find_group_stage_winners(groupA))

my_win_counter = {"England": 3, "Norway": 1, "Austria": 1, "Northern Ireland": 1}
for i in range(10):
    print(simulation.find_countries_to_advance_in_group(my_win_counter))
