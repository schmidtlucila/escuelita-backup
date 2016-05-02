require 'selenium-webdriver'
require 'byebug'
require 'dater'
require 'rest-client'

$date = Dater::Resolver.new("%Y-%m-%d","es",true)


country_data_ar = { "currency_code" => "ARS",
										"flights_keyword" => "Vuelos",
										"adult_word" => "Adulto",
										"children_word" => "Niño",
										"taxes_word" => "Imp",
										"charges_word" => "Cargos",
										"afip_word" => "AFIP",
										"url" => "http://www.despegar.com.ar"
									}

country_data_br = { "currency_code" => "BRL",
										"flights_keyword" => "Passagens",
										"adult_word" => "Adulto",
										"children_word" => "Criança",
										"url" => "http://www.decolar.com"
									}


$country_data = { "AR" => country_data_ar,
									"BR" => country_data_br
								}
# Here you have to put all setup (gems and files requiring, configuration, etc)





