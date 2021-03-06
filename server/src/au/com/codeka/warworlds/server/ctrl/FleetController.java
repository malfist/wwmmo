package au.com.codeka.warworlds.server.ctrl;

import java.sql.Statement;

import au.com.codeka.warworlds.server.RequestException;
import au.com.codeka.warworlds.server.data.SqlStmt;
import au.com.codeka.warworlds.server.data.Transaction;
import au.com.codeka.warworlds.server.model.Empire;
import au.com.codeka.warworlds.server.model.Fleet;
import au.com.codeka.warworlds.server.model.FleetUpgrade;
import au.com.codeka.warworlds.server.model.Star;

public class FleetController {
    private DataBase db;

    public FleetController() {
        db = new DataBase();
    }
    public FleetController(Transaction trans) {
        db = new DataBase(trans);
    }

    public Fleet createFleet(Empire empire, Star star, String designID, float numShips) throws RequestException {
        try {
            Fleet fleet = new Fleet(empire, star, designID, numShips);
            db.createFleet(fleet);
            star.getFleets().add(fleet);
            return fleet;
        } catch(Exception e) {
            throw new RequestException(e);
        }
    }

    public void addUpgrade(Star star, Fleet fleet, String upgradeID) throws RequestException {
        try {
            FleetUpgrade upgrade = new FleetUpgrade(star.getID(), fleet.getID(), upgradeID);
            db.addUpgrade(upgrade);
            fleet.getUpgrades().add(upgrade);
        } catch(Exception e) {
            throw new RequestException(e);
        }
    }

    /**
     * Removes the given number of ships from the given fleet. Possibly removes the whole fleet
     * if there's not enough ships left.
     */
    public void removeShips(Star star, Fleet fleet, float numShips) throws RequestException {
        if (fleet.getNumShips() <= numShips) {
            String sql = "DELETE FROM fleet_upgrades WHERE fleet_id = ?";
            try (SqlStmt stmt = db.prepare(sql)) {
                stmt.setInt(1, fleet.getID());
                stmt.update();
                star.getFleets().remove(fleet);
            } catch(Exception e) {
                throw new RequestException(e);
            }

            sql = "DELETE FROM fleets WHERE id = ?";
            try (SqlStmt stmt = db.prepare(sql)) {
                stmt.setInt(1, fleet.getID());
                stmt.update();
                star.getFleets().remove(fleet);
            } catch(Exception e) {
                throw new RequestException(e);
            }
        } else {
            String sql = "UPDATE fleets SET num_ships = num_ships - ? WHERE id = ?";
            try (SqlStmt stmt = db.prepare(sql)) {
                stmt.setDouble(1, numShips);
                stmt.setInt(2, fleet.getID());
                stmt.update();
                fleet.setNumShips(fleet.getNumShips() - numShips);
            } catch(Exception e) {
                throw new RequestException(e);
            }
        }
    }

    private static class DataBase extends BaseDataBase {
        public DataBase() {
            super();
        }
        public DataBase(Transaction trans) {
            super(trans);
        }

        public void createFleet(Fleet fleet) throws Exception {
            String sql = "INSERT INTO fleets (sector_id, star_id, design_id, empire_id," +
                    " num_ships, stance, state, state_start_time, notes)" +
                 " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (SqlStmt stmt = prepare(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, fleet.getSectorID());
                stmt.setInt(2, fleet.getStarID());
                stmt.setString(3, fleet.getDesignID());
                if (fleet.getEmpireKey() == null) {
                    stmt.setNull(4);
                } else {
                    stmt.setInt(4, fleet.getEmpireID());
                }
                stmt.setDouble(5, fleet.getNumShips());
                stmt.setInt(6, fleet.getStance().getValue());
                stmt.setInt(7, fleet.getState().getValue());
                stmt.setDateTime(8, fleet.getStateStartTime());
                stmt.setString(9, fleet.getNotes());
                stmt.update();
                fleet.setID(stmt.getAutoGeneratedID());
            }
        }

        public void addUpgrade(FleetUpgrade upgrade) throws Exception {
            String sql = "INSERT INTO fleet_upgrades (star_id, fleet_id, upgrade_id) VALUES (?, ?, ?)";
            try (SqlStmt stmt = prepare(sql)) {
                stmt.setInt(1, upgrade.getStarID());
                stmt.setInt(2, upgrade.getFleetID());
                stmt.setString(3, upgrade.getUpgradeID());
                stmt.update();
            }
        }
    }
}
