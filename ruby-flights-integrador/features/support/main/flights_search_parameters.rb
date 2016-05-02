class FlightsSearchParameters
	attr_accessor(:origin, :destination, :type, :departure, :return, :adults, :children, :country)

	def initialize parametros
		parametros.keys.each do |key|
			self.send("#{key}=", parametros[key])
  	end
	end
end