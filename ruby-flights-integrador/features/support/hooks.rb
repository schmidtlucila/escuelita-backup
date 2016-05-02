# encoding: utf-8

Before('@gui') do |scenario|
  @browser = Selenium::WebDriver.for :firefox
  @browser.manage.window.maximize
end

After('@gui') do |scenario|
  if scenario.failed?
    file_name = scenario.name.gsub(" ","-") + "#{Time.now.strftime('%Y-%m-%d')}" + ".png"
    @browser.save_screenshot("#{Dir.pwd}/reports/#{file_name}") if @browser
  end
  @browser.close
end

at_exit do
  # Actions after all scenarios execution (the end of entire execution)
end