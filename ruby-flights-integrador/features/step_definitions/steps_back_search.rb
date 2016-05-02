Dado(/^un servicio de FSM al que se le piden vuelos$/) do
  @service = FSMService.new
end


Dado(/^que realiza una búsqueda con los siguientes parámetros$/) do |table|
  @adults = table.hashes.first["adults"].to_i
  @children = table.hashes.first["children"].to_i
  @parameters = FlightsSearchParameters.new	table.hashes.first
end


Cuando(/^se convoca al servicio$/) do
  
end