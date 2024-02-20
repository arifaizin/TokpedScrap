data class Country(var name: String="", val population: String="") 

// Main class
data class ScrapingResult(val countries: MutableList<Country> = mutableListOf(), var count:Int = 0)
