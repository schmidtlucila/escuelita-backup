Entonces(/^en la página de checkout tenemos que$/) do
  
end


Entonces(/^la cantidad de secciones para infantes debe ser igual a la del detalle de la compra$/) do
  fail unless @page.children == @page.children_in_detail
end

Entonces(/^la cantidad de secciones para adultos debe ser igual a la del detalle de la compra$/) do
  fail unless @page.adults == @page.adults_in_detail
end

Entonces(/^todas las secciones de pasajeros deben tener un campo para nombre y otro para apellido$/) do
  fail unless @page.all_sections_have_first_and_last_name_fields
end
