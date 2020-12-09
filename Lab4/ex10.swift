var playerScore: Array<(String, String, Int)> = []
var totalScore = 0
let legalMoves = ["K", "P", "N"]

while true {
  print("Player 1:")
  let player1: String = readLine()!
  print("Player 2:")
  let player2: String = readLine()!

  if (player1 == "q") {
    break
  } else if (legalMoves.contains(player1) && legalMoves.contains(player2)) {
    switch(player1) {
      case player1 where player1 == player2:
        playerScore.append((player1, player2, totalScore))
      case player1 where (player1 == "K" && player2 == "N") ||
      (player1 == "N" && player2 == "P") ||
      (player1 == "P" && player2 == "K"):
        totalScore += 1
        playerScore.append((player1, player2, totalScore))
      case player1 where (player1 == "K" && player2 == "P") ||
      (player1 == "N" && player2 == "K") ||
      (player1 == "P" && player2 == "N"):
        totalScore -= 1
        playerScore.append((player1, player2, totalScore))
      default:
        print("Something went wrong ;)")
    }
  }

  print("Round over - total score:", playerScore)
}