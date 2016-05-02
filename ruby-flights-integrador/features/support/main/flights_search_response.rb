class FlightsSearchResponse

	attr_reader(:children, :adults, :taxes, :charges, :fees, :total)

	def initialize json_item

		prices = json_item["itinerary"]["itineraryPrice"]["all"].first
		child_price = prices["child"]
		if child_price.nil?
			@children = 0
		else
			@children = child_price["quantity"]
		end
		adult_price = prices["adult"]
		if adult_price.nil?
			@adults = 0
		else
			@adults = adult_price["quantity"]
		end
		@taxes = prices["total"]["taxes"]
		@charges = prices["total"]["charges"]
		@fees = prices["total"]["fees"]
		@total = prices["total"]["totalFare"]
	end


end