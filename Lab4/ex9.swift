let animalArr = ["Kot", "Pies", "Rybcia", "Mysz", "Kot", "Pies", "Pies", "Pies"]
var occArr: Array<(String, Int)> = []

func tupleIndexOf(_ x: String, _ myTuple: Array<(String, Int)>) -> Int {
  for i in 0..<myTuple.count {
    if myTuple[i].0 == x {
      return i
    }
  }

  return -1
}

for animal in animalArr {
  if !occArr.contains(where: {$0.0 == animal}) {
    occArr.append((animal, 1))
  } else {
    let occIndex = tupleIndexOf(animal, occArr)
    if (occIndex != -1) {
      occArr[occIndex].1 += 1
    }
  }
}

print(occArr)