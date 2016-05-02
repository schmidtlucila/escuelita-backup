module DatePicker

  def click_on_date(date)
  	@wait.until{@searchbox.find_element(:tag_name, "a").enabled?}
    months = @browser.find_elements(:class, "sbox-ui-datepicker-calendar-container")
    day = day_on_calendar(months, date)
    @wait.until{day.displayed?}
    day.click   
  end

  def day_on_calendar(months, date) 
    look_for_month date
    day = day_on_month(first_visible_month, date)
    return day
  end

  def day_on_month(month, date)
    days = month.find_elements(:tag_name, "a")
    days.select do |day|
      day.attribute("data-date") == date and day.displayed?
    end.first
  end

  def first_visible_month
    months = @browser.find_elements(:class, "sbox-ui-datepicker-calendar-container")
    months.each do |month|
      if month.displayed?
        return month
      end
    end
  end

  def previous_month_button
    @browser.find_element(:link_text, '‹')
  end

  def next_month_button
    @browser.find_element(:link_text, '›')
  end


  def look_for_month date   
    press_next_if_necessary date
    press_previous_if_necessary date
  end

  def press_previous_if_necessary date
    days = first_visible_month.find_elements(:tag_name, "a")
    if days.first.attribute("data-date") > date
      previous_month_button.click
      look_for_month date
    end
  end

  def press_next_if_necessary date
    days = first_visible_month.find_elements(:tag_name, "a")
    if days.last.attribute("data-date") < date
      next_month_button.click
      look_for_month date
    end
  end


end