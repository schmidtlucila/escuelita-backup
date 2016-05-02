
# encoding: utf-8

Dado(/^un usuario de '(.*)'$/) do |arg|
  @country = arg
end
  
Dado(/^que ingresa a Despegar$/) do
  @page = AllPages.new(@browser, @country)
  @page.open $country_data[@country]["url"]
  @page.close_popup
end

Dado(/^que ingresa a vuelos a realizar una búsqueda$/) do
  @page = @page.go_to "vuelos" 
end

Dado(/^de tipo roundtrip$/) do
  @page.uncheck_one_way
end

Dado(/^de tipo oneway$/) do
  @page.check_one_way
end


Dado(/^con origen '(.*)'$/) do |arg|
  @page.set_origin arg
end

Dado(/^con destino '(.*)'$/) do |arg|
  @page.set_destination arg
end

Dado(/^para (\d+) adultos$/) do |arg|
  @adults = arg
  @page.set_adults arg
end

Dado(/^para (\d+) infantes$/) do |arg|
  @children = arg
  @page.set_children arg
end

Dado(/^con fecha de salida '(.*)'$/) do |arg1|
  @page.set_departure_date arg1
end

Dado(/^con fecha de regreso '(.*)'$/) do |arg1|
    @page.set_return_date arg1
end

Cuando(/^hace click en buscar$/) do
  @page = @page.click_search_button
end

Dado(/^que ingresa a vuelos y realiza una búsqueda con los siguientes parámetros$/) do |table|
  @adults = table.hashes.first["adults"].to_i
  @children = table.hashes.first["children"].to_i
  @type = table.hashes.first["type"]
  step "que ingresa a vuelos a realizar una búsqueda"
  step "con fecha de salida '#{table.hashes.first["departure"]}'"
  step "de tipo #{@type}"
  step "con origen '#{table.hashes.first["origin"]}'"
  step "con destino '#{table.hashes.first["destination"]}'"
  step "para #{@children} infantes"
  step "para #{@adults} adultos"
  step "hace click en buscar"
end

Cuando(/^selecciona el primer resultado y hace click en el botón de comprar$/) do
  @page.populate_result
  @page.select_first_outbound_choice_on_first_result
  unless @type == "oneway"
    @page.select_first_inbound_choice_on_first_result
  end
  @page = @page.click_buy_button_on_first_result
end
