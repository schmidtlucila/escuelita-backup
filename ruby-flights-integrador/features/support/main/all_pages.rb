class AllPages

  def initialize(browser, country)
    @browser = browser
    @country = country
  end

  def open url = nil
    if url
      @browser.get url
    else
      @browser.get "http://despegar.com"
    end
  end

  def close_popup
    popup_element.click if popup_element?
  end

  def popup_element
    @browser.find_element(:class, "nibbler-common-overlay-close")
  end

  def popup_element?
    begin
      popup_element and true
    rescue
      false
    end
  end

  def go_to donde
    case donde
    when /vuelos|flights/i
      @browser.find_element(:link_text, $country_data[@country]["flights_keyword"]).click
      return FlightsSearchPage.new(@browser, @country)
    when /hotel/i
      @browser.find_element(:link_text, "Hoteles").click
      HotelsSearchPage.new(@browser, @country)
    end
  end



end