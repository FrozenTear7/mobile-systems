class Animal: CustomStringConvertible {
  var name: String
  var age: Int
  var species: String

  init(_ name: String, _ age: Int, _ species: String) {
    self.name = name
    self.age = age
    self.species = species
  }

  func move() -> Void {
    print("Bro I'm", name, "and I'm moving")
  }

  var description: String {
    return "(\(name), \(age), \(species))"
  }
}

class Fish: Animal {
  override func move() -> Void {
    print("Bro I'm", name, "and I'm swimming")
  }
} 

class Dog: Animal {
  override func move() -> Void {
    print("Bro I'm", name, "and I'm running")
  }
} 

let kotek = Animal("Kot", 12, "Kotowate")
kotek.move()
print(kotek)

let rybcia = Fish("Neon Tetra", 2, "Płetwowe")
rybcia.move()
print(rybcia)

let piesek = Dog("Doge", 15, "Doge :)")
piesek.move()
print(piesek)

let rybciaPremium: Animal = Fish("Neon Tetra Premium Red", 2, "Płetwowe")
rybciaPremium.parent.move()
print(rybciaPremium)