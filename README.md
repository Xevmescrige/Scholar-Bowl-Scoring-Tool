# Scholar Bowl Scoring Tool
The purpose of this tool is to help reduce human error in scorekeeping as well as conserve paper and make tabbing at tournaments faster and more efficient.  Every player, question result, and bonus will be assigned a button, and at the end of the match, all of the data will be output to a formatted spreadsheet.  This tool is currently using the standard NAQT format with powers, negs, and rebounding tossups with 3-part rebounding bonuses.


## Getting Started
### Setup
scorer.jar is a standalone runnable JAR file -- however, the program creates an output folder at the end of the match.  To help with organization, it is recommended that the user create a folder containing the JAR, as this additional folder is added to the directory the program is in.
The .xls file created by this program is output under /game_results_scoresheets here:
```
./scorer.jar
./game_results_scoresheets/scoresheets_room-<RoomNumber>_round-<RoundNumber>.xls
```
The filetree should look something like this:
```
./FolderContainingScorer/scorer.jar
./FolderContainingScorer/game_results_scoresheets/scoresheets_room-<RoomNumber>_round-<RoundNumber>.xls
```
## Using the Tool
### Room Setup Screen
This is the screen the scorekeeper will see when the tool first launches.  There are labelled text input boxes -- fill them out as such:
```
ROOM: This is the current room number (Ex: 213, 115, 327, etc.)  Only enter a number into this box
MODERATOR: This is the moderator's name.  This may be the same as the scorekeeper's name
ROUND: This is the current round number (Ex: 1, 2, 3, 4...)  Only enter a number into this box
PACKET: The scorekeeper may enter the round number or some other info about the round into this box (Ex: Byround)
SCOREKEEPER: This is the scorekeeper's name.  This may be the same as the moderator's name.
```

When the scorekeeper is done, click the `[NEXT -->]` button at the top of the screen.

### Team Setup Screen
On this screen's menubar, the scorekeeper should see:
```
<-- BACK] - This button will take the scorekeeper back to the setup screen
[TEAM A] - This button will set the team that's currently being edited to team A.
[TEAM B] - This button will set the team that's currently being edited to team B.
    [TEAM A] and [TEAM B]'s text will change once team A and B's names have been changed.
[START MATCH] - This button will start the match and take the scorekeeper to the question screen.
```
On the screen itself, there are buttons corresponding to the team's name and the 4 player's names.  The screen will initialize to team A by default, so click on these buttons and set the team and player names, then click the `[TEAM B]` button at the top of the screen and repeat this process.  If there are fewer than 4 players on a team, don't set more names than there are players.  For example, if there are 2 players, only click on 2 buttons to set player names -- the remaining "players" will be marked as substitutes

Once the scorekeeper has done this, click the `[START MATCH]` button.

### Match Screen
On this screen's menubar, the scorekeeper should see: 
```
[<-- BACK TO SETUP] - This button will take the scorekeeper back to the team and player name screen
[TIMEOUT] - This button will take the scorekeeper to the timeout screen, which will allow them to make substitutions
[<-- PREVIOUS QUESTION] - This button will take the scorekeeper to the previous question
[VIEW SCOREBOARD] - This button will bring up the scoreboard
[NEXT QUESTION -->] This button will take the scorekeeper to the next question
[PROTEST] - This button will bring up a dialog prompting the scorekeeper about protests
```
On the screen itself, there are buttons corresponding to each player.  When a player buzzes in, click the button corresponding to their name.
If the question goes dead (if either zero or one team answers), click the  
`[NEXT QUESTION -->]` button.

However, if both teams answer incorrectly, the tool will automatically progress to the next question.

If the scorekeeper finds that they have made an error in a question, they may click the `[<-- PREVIOUS QUESTION]` button until they've returned to the erroneous question and make the adjustment.  A correct or power answer will override any other correct/powers in for that question, and a neg will override any negs for that question.  Any answer will override all other values for that question for the answering team. For example, if Team A's player 3 has answered incorrectly (no neg), and the scorekeeper corrects it so that Team A's player 1 powered, Team A player 3 will no longer have that incorrect mark and any other correct/powers for that question will be overridden.

If a team protests a question, click the `[PROTEST]` button and verify the protest.  At the end of the match, click the `[PROTEST]` button again to check what questions have been protested - that is, if the protest would affect the outcome of the match.

`[VIEW SCOREBOARD]` brings up a new window -- there's a button to `[REFRESH]` the scores if the scoreboard is outdated.

### Timeout and Halftime
The timeout and halftime screens are the same in terms of functionality.  
This screen's buttons:
```
[SCOREBOARD] - Brings up the scoreboard
[<-- BACK] - Takes the scorekeeper back to the question screen
[CONTINUE -->] - If timeout: returns to question panel; If halftime: proceeds to next question
[<PLAYERNAME>] - Substitution buttons
```
The halftime screen will appear after question 10. 

Clicking on a player's name while in the timeout/halftime screen will sub them out for a new player.  If a fifth or sixth player haven't been added, the scorekeeper will be prompted on whether or not they want to add a fifth/sixth player.  If they do not wish to add a player, a dropdown menu will appear with the existing players' names. 

### End Round
Ending the round will generate a scoresheet.  Once this is done and you have gone over protests, you may exit the program and relaunch it for a new round.

On the final scoresheet, "0.001" indicates a round in which a player has been substituted, "0.0001" indicates an incorrect - no neg question.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [POI-HSSF](https://poi.apache.org/spreadsheet/) - Apache's framework to access Microsoft's .xls files

## Authors

* **Viet Nguyen** - *Initial work* - [Xevmescrige](https://github.com/Xevmescrige)

See also the list of [contributors](https://github.com/Xevmescrige/Scholar-Bowl-Scoring-Tool/graphs/contributors) who participated in this project.

## License

This project is licensed under the GPL-3.0 - see the [LICENSE.md](https://github.com/Xevmescrige/Scholar-Bowl-Scoring-Tool/blob/master/LICENSE) file for details

## Acknowedgements

* L. McKenzie
* P. O'Keefe
* J. Hill
* B. Ritter

