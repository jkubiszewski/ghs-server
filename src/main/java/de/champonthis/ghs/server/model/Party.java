/**
 * 
 */
package de.champonthis.ghs.server.model;

import java.util.LinkedList;

/**
 * The Class Party.
 */
public class Party {

	private String name;
	private String location;
	private String notes;
	private String achievements;
	private int reputation = 0;
	private int prosperity = 0;
	private LinkedList<Identifier> scenarios;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the notes.
	 *
	 * @param notes the new notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Gets the achievements.
	 *
	 * @return the achievements
	 */
	public String getAchievements() {
		return achievements;
	}

	/**
	 * Sets the achievements.
	 *
	 * @param achievements the new achievements
	 */
	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	/**
	 * Gets the reputation.
	 *
	 * @return the reputation
	 */
	public int getReputation() {
		return reputation;
	}

	/**
	 * Sets the reputation.
	 *
	 * @param reputation the new reputation
	 */
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	/**
	 * Gets the prosperity.
	 *
	 * @return the prosperity
	 */
	public int getProsperity() {
		return prosperity;
	}

	/**
	 * Sets the prosperity.
	 *
	 * @param prosperity the new prosperity
	 */
	public void setProsperity(int prosperity) {
		this.prosperity = prosperity;
	}

	/**
	 * Gets the scenarios.
	 *
	 * @return the scenarios
	 */
	public LinkedList<Identifier> getScenarios() {
		return scenarios;
	}

	/**
	 * Sets the scenarios.
	 *
	 * @param scenarios the new scenarios
	 */
	public void setScenarios(LinkedList<Identifier> scenarios) {
		this.scenarios = scenarios;
	}

}