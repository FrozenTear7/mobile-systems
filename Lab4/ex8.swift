let nameArr = ["Leszek", "Paweł", "Ania", "Aleksandra", "Piotr", "Agnieszka"]

print(nameArr.filter {
  $0.last! == "a"
})

print(nameArr.filter {
  $0.last! != "a"
})

print(nameArr.map {
  $0.last! != "a" ? "Pan " + $0 : "Pani " + $0
})

print("Liczba kobiet:", nameArr.reduce(0) {
  if ($1.last! != "a") {
    return $0 + 1
  }

  return $0
})