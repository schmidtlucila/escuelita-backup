Entonces(/^para el primer resultado del servicio$/) do
  @first_result = @service.search @parameters
  fail if @first_result.nil?
end

Entonces(/^para el primer resultado de la página$/) do
  @page.populate_result
  @first_result = @page.best_result
end


Entonces(/^la cantidad de infantes debe ser igual a la de la búsqueda$/) do
	fail unless @first_result.children == @children.to_i
end

Entonces(/^la cantidad de adultos debe ser igual a la de la búsqueda$/) do
  unless @first_result.adults == @adults.to_i
  	puts "En el restulado hay #{@first_result.adults} adultos"
  	puts "En la búsqueda hubo #{@adults} adultos"
  	fail
  end
end

Entonces(/^la suma de los precios e impuestos debe ser igual al total$/) do
	fail unless @first_result.children_price + @first_result.adults_price + @first_result.taxes + @first_result.charges + @first_result.afip == @first_result.total
end

Entonces(/^la tarifa debe ser no nula$/) do
  fail if @first_result.base_price.nil?
end
