/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package framework;

public abstract class GameModel {

  public GameUI m_game;

  protected GameModel() {
  }

  public GameUI getGameUI() {
    return m_game;
  }
  
  /**
   * Simulation step.
   * 
   * @param now
   *          is the current time in milliseconds.
   */
  public abstract void step(long now);
  
  public abstract void shutdown();
}
