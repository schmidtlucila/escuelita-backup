require_relative 'formatter'

class ResultCluster < AllPages
	include Formatter

  attr_reader(:adults, :children, :adults_price, :children_price, :taxes, :charges, :afip, :total, :base_price)

  def initialize(cluster, country)
    @wait = Selenium::WebDriver::Wait.new(:timeout => 20)
    @country = country
    @cluster = cluster
    @adults = 0
    @children = 0
    @adults_price = 0
    @children_price = 0
    @price_detail = @cluster.find_element(:class, "fare-detail")
    @base_price = @price_detail.find_element(:class, "fare").find_element(:class, $country_data[@country]["currency_code"]).find_element(:class, "price-amount")
    if @country == "AR"
    	initialize_details
    end
  end


  def initialize_details
	details = @cluster.find_elements(:class, "item-detail")
	prices = []
	@cluster.find_elements(:class, "item-price").each do |price|
		amount = price.find_element(:class, "price-amount")
		if amount.displayed?
			prices << amount
		end
	end
	
	if details.size != prices.size
		byebug
	end

	details.size.times do |i|
		detail_string = details[i].text
		price_string = prices[i].text
		initialize_children(detail_string, price_string)
		initialize_adults(detail_string, price_string)
		initialize_taxes(detail_string, price_string)
		initialize_afip(detail_string, price_string)
		initialize_charges(detail_string, price_string)
		initialize_total(detail_string, price_string)
		end
  end

  def initialize_children(detail_string, price_string)
    keyword = $country_data[@country]["children_word"]
  	if detail_string.include? keyword
  		@children = detail_string.split(" ")[0].to_i
  		@children_price = plain_number(price_string)
  	end
  end

  def initialize_adults(detail_string, price_string)
  	keyword = $country_data[@country]["adult_word"]
    if detail_string.include? keyword
    	@adults = detail_string.split(" ")[0].to_i
  		@adults_price = plain_number(price_string)

  	end
  end

  def initialize_taxes(detail_string, price_string)
  	keyword = $country_data[@country]["taxes_word"]
    if detail_string.include? keyword
  		@taxes = plain_number(price_string)

  	end
  end

  def initialize_afip(detail_string, price_string)
  	keyword = $country_data[@country]["afip_word"]
    if detail_string.include? keyword
  		@afip = plain_number(price_string)
  	end
  end

  def initialize_charges(detail_string, price_string)
  	keyword = $country_data[@country]["charges_word"]
    if detail_string.include? keyword
  		@charges = plain_number(price_string)
  	end
  end

  def initialize_total(detail_string, price_string)
  	if detail_string.include? "Total"
  		@total = plain_number(price_string)
  	end
  end

  def select_first_outbound_choice
    @cluster.find_element(:class, "outbound").find_element(:class, "radio").click
  end

  def select_first_inbound_choice
    @cluster.find_element(:class, "inbound").find_element(:class, "radio").click
  end

  def click_buy_button
    buttons = @cluster.find_elements(:class, "btn-buy")
    buttons.each do |button|
      if button.displayed?
        button.click
      end
    end
  end

end