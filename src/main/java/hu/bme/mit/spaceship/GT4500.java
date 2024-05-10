package hu.bme.mit.spaceship;

import hu.bme.mit.spaceship.TorpedoStore;

/**
 * A simple spaceship with two proton torpedo stores and four lasers
 */
public class GT4500 implements SpaceShip {

  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;

  private boolean wasPrimaryFiredLast = false;

  // Modified constructor to accept TorpedoStore instances
  public GT4500(TorpedoStore primaryTorpedoStore, TorpedoStore secondaryTorpedoStore) {
    this.primaryTorpedoStore = primaryTorpedoStore;
    this.secondaryTorpedoStore = secondaryTorpedoStore;
  }

  public boolean fireLaser(FiringMode firingMode) {
    // TODO not implemented yet
    return false;
  }

  @Override
  public boolean fireTorpedo(FiringMode firingMode) {
    boolean firingSuccess = false;

    switch (firingMode) {
      case SINGLE:
        if (wasPrimaryFiredLast) {
          // try to fire the secondary first
          if (!secondaryTorpedoStore.isEmpty()) {
            firingSuccess = secondaryTorpedoStore.fire(1);
            wasPrimaryFiredLast = false;
          } else {
            // although primary was fired last time, but the secondary is empty
            // thus try to fire primary again
            if (!primaryTorpedoStore.isEmpty()) {
              firingSuccess = primaryTorpedoStore.fire(1);
              wasPrimaryFiredLast = true;
            }
          }
        } else {
          // try to fire the primary first
          if (!primaryTorpedoStore.isEmpty()) {
            firingSuccess = primaryTorpedoStore.fire(1);
            wasPrimaryFiredLast = true;
          } else {
            // although secondary was fired last time, but primary is empty
            // thus try to fire secondary again
            if (!secondaryTorpedoStore.isEmpty()) {
              firingSuccess = secondaryTorpedoStore.fire(1);
              wasPrimaryFiredLast = false;
            }
          }
        }
        break;

      case ALL:
        // try to fire both of the torpedo stores
        //TODO implement feature

        break;
    }

    return firingSuccess;
  }
}
