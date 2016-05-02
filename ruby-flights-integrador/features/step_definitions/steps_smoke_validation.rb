Cuando(/^se dirige a vuelos$/) do
  @page = @page.go_to "vuelos"
end

Entonces(/^la caja de búsqueda de la página$/) do
  
end

Entonces(/^debe tener el título '(.*)'$/) do |arg|
  fail unless @page.title == arg
end

Entonces(/^debe tener un campo para ingresar el origen con el placeholder '(.*)'$/) do |arg|
  fail unless @page.origin_placeholder == arg
end

Entonces(/^debe tener un checkbox de sólo ida$/) do
  fail unless @page.has_checkbox?
end


Entonces(/^debe tener un campo para ingresar el destino con el placeholder '(.*)'$/) do |arg|
  fail unless @page.destination_placeholder == arg
end

Entonces(/^debe tener un campo para ingresar la fecha de partida con el placeholder '(.*)'$/) do |arg|
  fail unless @page.departure_date_placeholder == arg
end

Entonces(/^debe tener un campo para ingresar la fecha de retorno con el placeholder '(.*)'$/) do |arg|  
  fail unless @page.return_date_placeholder == arg
end

Entonces(/^debe tener un select de adultos$/) do
  fail unless @page.has_adults_select?
end

Entonces(/^debe tener un select de menores$/) do
  fail unless @page.has_children_select?
end

Entonces(/^debe tener un botón de búsqueda$/) do
  fail unless @page.has_search_button?
end