package com.gojek.parkinglot.strategy;

import com.gojek.parkinglot.model.Slot;

public interface SlotAllocationStrategy {
      public void addSlot(Slot slot);
      public int Slot();
      public void removeSlot(Slot slot);
}
