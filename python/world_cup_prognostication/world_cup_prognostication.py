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

    def knockout_stage(self):
        """
        https://en.wikipedia.org/wiki/UEFA_Women%27s_Euro_2022#Knockout_stage
        :return:
        """
        group_a_winners = self.find_group_stage_winners(self.brackets[0])
        group_b_winners = self.find_group_stage_winners(self.brackets[1])
        group_c_winners = self.find_group_stage_winners(self.brackets[2])
        group_d_winners = self.find_group_stage_winners(self.brackets[3])
        # print("=== Group stage winners: ===")
        # print(group_a_winners, group_b_winners, group_c_winners, group_d_winners)

        # Quarter-finals 1 <-> 2, 3 <-> 4
        quarter_1 = [group_a_winners[0], group_b_winners[1]]
        quarter_1_winner = self.find_winner(quarter_1[0], quarter_1[1])
        quarter_2 = [group_b_winners[0], group_a_winners[1]]
        quarter_2_winner = self.find_winner(quarter_2[0], quarter_2[1])
        quarter_3 = [group_c_winners[0], group_d_winners[1]]
        quarter_3_winner = self.find_winner(quarter_3[0], quarter_3[1])
        quarter_4 = [group_d_winners[0], group_c_winners[1]]
        quarter_4_winner = self.find_winner(quarter_4[0], quarter_4[1])

        # print("=== Quarter final winners: ===")
        # print(quarter_1_winner, quarter_2_winner, quarter_3_winner, quarter_4_winner)

        # Semi-finals
        semi_1 = [quarter_3_winner, quarter_1_winner]
        semi_1_winner = self.find_winner(semi_1[0], semi_1[1])
        semi_2 = [quarter_4_winner, quarter_2_winner]
        semi_2_winner = self.find_winner(semi_2[0], semi_2[1])

        # print("=== Semi final winners: ===")
        # print(semi_1_winner, semi_2_winner)

        # Finals
        finals_winner = self.find_winner(semi_1_winner, semi_2_winner)
        # print("=== Finals winner: === ")
        # print(finals_winner)
        return finals_winner

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
        # print("Group stage win counter: " + str(win_counter))
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

    def find_winner(self, team_a: str, team_b: str) -> str:
        random_probability = random.random()
        if random_probability <= self.probability_of_win_a(self.team_ratings[team_a], self.team_ratings[team_b]):
            return team_a
        else:
            return team_b

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
simulation_win_count = dict.fromkeys(simulation.team_ratings, 0)

print(simulation_win_count)
for i in range(1000):
    simulation_win_count[simulation.knockout_stage()] += 1

sorted_win_count = sorted(simulation_win_count.items(), key=lambda x: -x[1])
print(sorted_win_count)

"""
1,000,000 simulations:
[('France', 263686), ('Sweden', 243432), ('Germany', 188440), ('Netherlands', 122518), ('England', 82331), ('Spain', 63124), ('Italy', 16369), ('Norway', 11817), ('Denmark', 4798), ('Iceland', 2413), ('Austria', 656), ('Belgium', 266), ('Switzerland', 120), ('Portugal', 23), ('Finland', 7), ('Northern Ireland', 0)]

"""
