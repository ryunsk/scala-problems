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
    wins = dict.fromkeys(team_ratings, 0)  # Copy teams with count 0

    def find_group_stage_winners(self, teams: List[str]) -> List[str]:
        """
        Finds two teams in a group who will advance to the knockout stage.
        For simplicity there are no draws and tiebreakers.
        :param teams: List of teams in the group stage
        :return: A list with two teams
        """
        win_counter = dict.fromkeys(teams, 0)

        return

    def probability_of_win_a(self, a_rating: int, b_rating: int):
        m = (b_rating - a_rating) / 400
        return 1 / (1 + 10 ** m)

    def is_winner(self, team_a: str, team_b: str) -> bool:
        """
        Plays a game between two team and checks if team A is the winner.
        :param team_a:
        :param team_b:
        :return: Boolean to check if team A won. False means team B won.
        """
        random_probability = random.random()
        return random_probability <= self.probability_of_win_a(self.team_ratings[team_a], self.team_ratings[team_b])


sim1 = Simulation()
print(sim1.team_ratings)

"""
P(A) = 1/(1+10^m) 
where m is the rating difference (rating(B)-rating(A)) divided by 400
"""
