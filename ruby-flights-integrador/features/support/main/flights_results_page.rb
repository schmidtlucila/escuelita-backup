class FlightsResultPage < AllPages
  def initialize(browser, country)
    @country = country
    @best_result = nil
    @wait = Selenium::WebDriver::Wait.new(:timeout => 60)
    @browser = browser
    wait_results
  end

  def best_result
    @best_result
  end

  def wait_results
  	begin 
      @wait.until{ @browser.find_element(:class, "matrix-container").displayed? }
    rescue
      puts "Hubo timeout para la matriz de precios, intentando de todos modos."
    end
      @wait.until{ @browser.find_element(:class, "fare-detail").displayed? }
  end

  def populate_result
  	wait_results
    clusters = @browser.find_elements(:class, "cluster")
    initialize_best_result_with clusters.first if clusters.size > 0
  end

  def initialize_best_result_with cluster
    @best_result = ResultCluster.new(cluster, @country)
  end

  def select_first_outbound_choice_on_first_result
    @best_result.select_first_outbound_choice
  end

  def select_first_inbound_choice_on_first_result
    @best_result.select_first_inbound_choice
  end

  def click_buy_button_on_first_result
    close_fucking_popups
    @best_result.click_buy_button
    FlightsCheckoutPage.new(@browser, @country)
  end

  def close_fucking_popups
  end
end