package com.kalyan.cucumber.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GenericStepDefs {

	@Given("^I navigate to \"([^\"]*)\"$")
	public void i_navigate_to(String arg1) throws Throwable {

	}

	@When("^I enter \"([^\"]*)\"$")
	public void i_enter(String arg1) throws Throwable {

	}

	@When("^I click magnifying glass icon$")
	public void i_click_magnifying_glass_icon() throws Throwable {

	}

	@Then("^I can find string \"([^\"]*)\" in page$")
	public void i_can_find_string_in_page(String arg1) throws Throwable {

	}
	
	@Given("^I navigate1 to \"([^\"]*)\"$")
	public void i_navigate1_to(String arg1) throws Throwable {

	}


	@When("^I click1 magnifying glass icon$")
	public void i_click1_magnifying_glass_icon() throws Throwable {

	}

	@Then("^I can1 find string \"([^\"]*)\" in page$")
	public void i_can1_find_string_in_page(String arg1) throws Throwable {

	}
	
	@When("^I enter(\\d+) \"([^\"]*)\"$")
	public void i_enter(int arg1, String arg2) throws Throwable {
	   
	}

}
