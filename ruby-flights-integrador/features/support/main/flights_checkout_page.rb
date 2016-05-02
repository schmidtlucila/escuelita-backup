require_relative 'all_pages'

class FlightsCheckoutPage < AllPages

	attr_reader(:children, :adults, :children_in_detail, :adults_in_detail)

	def initialize(browser, country)
		@browser = browser
		@country = country
		@wait = Selenium::WebDriver::Wait.new(:timeout => 20)
		wait_components
		initialize_passenger_section
		initialize_detail_section
	end

	def wait_components
 		@wait.until{@browser.find_element(:class, "flights-button-red").displayed?}
	end

	def initialize_detail_section
		details = @browser.find_element(:class, "fare-detail")

		@adults_in_detail = adult_quantity_in details
		@children_in_detail = children_quantity_in details
	end

	def adult_quantity_in details
		detail_text = details.find_element(:class, "adult_fare").find_element(:class, "description").text
		take_first_number_between_parenthesis detail_text
	end

	def children_quantity_in details
		detail_text = details.find_element(:class, "child_fare").find_element(:class, "description").text
		take_first_number_between_parenthesis detail_text
	end

	def take_first_number_between_parenthesis text
		text.split("(")[1].split(")")[0].to_i
	end

	def initialize_passenger_section
		@adults = 0
		@children = 0
		
		@passenger_data_sections = @browser.find_element(:id, "passengers").find_elements(:class, "passenger")
		initialize_children_and_adults
	end

	def initialize_children_and_adults
		adult_word = $country_data[@country]["adult_word"]
		children_word = $country_data[@country]["children_word"]

		@passenger_data_sections.each do |section|
			reference = section.find_element(:class, "reference").text
			if reference.include? adult_word
				@adults = @adults + 1
			end
			if reference.include? children_word
				@children = @children + 1
			end
		end
	end

	def all_sections_have_first_and_last_name_fields
		@passenger_data_sections.each do |section|
			first_name_field = section.find_element(:class, "input-passenger-first-name")
			last_name_field = section.find_element(:class, "input-passenger-last-name")

			unless first_name_field.displayed? and last_name_field.displayed?
				return false
			end
		end

		return true
	end

end