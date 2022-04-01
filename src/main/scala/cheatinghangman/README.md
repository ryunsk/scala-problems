# Cheating Hangman

https://programmingpraxis.com/2011/12/27/cheating-hangman/

- Choose a word length
- List all possible answers
- Player guesses a letter
- Delete as few words from the answer consistent with the guess

## Easier logic

- Choose a word length
- Choose a random word as an answer
- Player guesses a letter
- If a letter is in the word, try to change the word
    - Bonus: Reveal the letter if it has better odds of "cheating"
- The word change must be consistent with the previous guesses.
    - e.g. if 'a' is not in the word then the new word must not contain 'a'

# Improvements

- Keep the position of the guessed letters the same
- Ask for player's input again if guess is not one letter long
- Move print statements to the `Display` class