class FSMService


  def initialize
    @url_base = "http://lb.despegar.it/fsm-service/service/items?searchtype={SEARCH_TYPE}&country={COUNTRY}&brand={BRAND}&product={PRODUCT}&adults={ADULTS}&childs={CHILDREN}&itemtype={ITEM_TYPE}&from={FROM}&to={TO}&departure={DEPARTURE_DATE}&return={RETURN_DATE}&matrixfareselectortype={FARE_SELECTOR}&channel={CHANNEL}"
    @brand_default = 0
    @product_default = 0
    @itemtype_default = "SINGLETYPE"
    @fare_selector_default = "TOTALFARE"
    @channel_default = "site"
  end

  def search parameters
    url = armar_url_para parameters
    response = JSON.parse(RestClient.get url)
    FlightsSearchResponse.new response["items"].first
  end

  def armar_url_para parametros
    url = @url_base
    url.gsub!("{SEARCH_TYPE}", parametros.type)
    url.gsub!("{COUNTRY}", parametros.country)
    url.gsub!("{BRAND}", @brand_default.to_s)
    url.gsub!("{PRODUCT}", @product_default.to_s)
    url.gsub!("{ADULTS}", parametros.adults.to_s)
    url.gsub!("{CHILDREN}", parametros.children.to_s)
    url.gsub!("{ITEM_TYPE}", @itemtype_default)
    url.gsub!("{FROM}", parametros.origin)
    url.gsub!("{TO}", parametros.destination)
    url.gsub!("{DEPARTURE_DATE}", $date.for(parametros.departure))
    if parametros.type == "roundtrip"
      url.gsub!("{RETURN_DATE}", $date.for(parametros.return))
    else
      url.gsub!("&return={RETURN_DATE}", "")
    end
    url.gsub!("{FARE_SELECTOR}", @fare_selector_default)
    url.gsub!("{CHANNEL}", @channel_default)

    url
  end

end