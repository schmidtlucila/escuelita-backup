require_relative 'date_picker'
require_relative 'all_pages'

class FlightsSearchPage < AllPages
  include DatePicker
  def initialize(browser, country)
    @wait = Selenium::WebDriver::Wait.new(:timeout => 20)
    @country = country
    @browser = browser
    @searchbox = @browser.find_element(:class, "sbox-flights-container")
    @button = @searchbox.find_element(:class, "sbox-search")
    @one_way = @searchbox.find_element(:id, "flights-going")
    @origin = @searchbox.find_element(:class, "sbox-origin")
    @destination = @searchbox.find_element(:class, "sbox-destination")
    @adults = @searchbox.find_element(:class, "sbox-passengers-adults-select")
    @children = @searchbox.find_element(:class, "sbox-passengers-childrens-select")
    @departure_date = @searchbox.find_element(:class, "sbox-datein")
    @return_date = @searchbox.find_element(:class, "sbox-dateout")
    @title = @searchbox.find_element(:class, "sbox-ui-title").text
  end


  def has_search_button?
    @button.displayed?
  end

  def has_checkbox?
    @one_way.displayed?
  end

  def has_adults_select?
    @adults.displayed?
  end

  def has_children_select?
    @children.displayed?
  end

  def title
    @title
  end

  def departure_date_placeholder
    @departure_date.attribute("placeholder")
  end

  def return_date_placeholder
    @return_date.attribute("placeholder")
  end

  def origin_placeholder
    @origin.attribute("placeholder")
  end

  def destination_placeholder
    @destination.attribute("placeholder")
  end


  def one_way?
    @one_way.selected?
  end

  def uncheck_one_way
    @one_way.click if self.one_way?
  end

  def check_one_way
    @one_way.click unless self.one_way?
  end

  def set_origin city
    @origin.send_keys city
    @wait.until{ @searchbox.find_element(:class, "sbox-ui-autocomplete-list").find_element(:class, "item").displayed? }
    select_appropiate_item(city, @origin)
  end

  def select_appropiate_item(city, field)
    options = @searchbox.find_element(:class, "sbox-ui-autocomplete-list").find_elements(:class, "item")
    options.each do |option|
      city_name = option.text.split(",")[0]
      if option.displayed? and city_name.include? city
        option.click
        return
      end
    end
    field.send_keys:return
  end

  def set_destination city
    @destination.send_keys city
    @wait.until{ @searchbox.find_element(:class, "destination-container").find_element(:class, "sbox-ui-autocomplete-list").find_element(:class, "item").displayed? }
    select_appropiate_item(city, @destination)
  end

  def set_adults quantity
    @adults.send_keys quantity.to_s
  end

  def set_children quantity
    @children.send_keys quantity.to_s

    #Next line is a workaround for making appear the combos of children-age
    #(I don't know why they don't appear except I make an interaction with the windows)

    @children.send_keys:return


    if quantity.to_i > 0
      @wait.until do
        @searchbox.find_element(:class, "sbox-children").displayed?
      end
      children_options = @searchbox.find_elements(:class, "sbox-children")

      children_options.each do |options|
        if options.displayed?
          ages = Selenium::WebDriver::Support::Select.new(options)
          ages.select_by(:value, "C")
        end
      end
    end
  end

  def select_date_in_field(date, field)
    field.click
    click_on_date(date)
  end

  def set_departure_date future_moment
    select_date_in_field($date.for(future_moment),@departure_date)
  end

  def set_return_date future_moment
    select_date_in_field($date.for(future_moment),@return_date)
  end

  def click_search_button
    @button.click
    FlightsResultPage.new(@browser, @country)
  end 

end