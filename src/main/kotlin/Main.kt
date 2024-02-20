
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.extractIt
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.a
import it.skrape.selects.html5.table
import it.skrape.selects.html5.td
import it.skrape.selects.html5.tr

val website_url = "https://www.tokopedia.com/search?st=&q=filter&srp_component_id=02.01.00.00&srp_page_id=&srp_page_title=&navsource="
val countries = skrape(HttpFetcher) {
    request {
        // Tell skrape{it} which URL to fetch data from
        url = website_url
    }


    extractIt<ScrapingResult> { results ->
        htmlDocument{
            // Main function where you'll parse web data
            val countryRows = table(".wikitable") {
                tr{
                    findAll{this}
                }

            }
            countryRows
                .drop(2)  // Remove the first two elements; these are just the table header and subheader
                .map{
                    // Define variables to hold name and population
                    var name: String =""
                    var population: String=""
                    it.a{
                        findFirst(){   // Find the first <a> tag
                            name = text    // Extract its text (this is the name of the country)
                            println("Name - $text ")
                        }
                    }
                    it.td{
                        findSecond(){    // Find the second <td> tag
                            population = text   // Extract its text (this is the population of the country)
                            println("Population - $text \n")
                        }
                    }
                    results.countries.add(Country(name,population))   // Create a country and add it to the results object
                    results.count = results.countries.size  // Get the number of countries and add it to the results object
                }
        }
    }
}

fun main(args: Array<String>) {
    println(countries.toString())
}