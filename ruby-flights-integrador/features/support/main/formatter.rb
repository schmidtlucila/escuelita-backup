module Formatter

	def plain_number string_number
    string_number.gsub(" ", "").gsub("$", "").gsub(".", "").to_i
	end

end