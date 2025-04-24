package model.objects;

import java.util.Map;

/**
 * RoomManager contains all rooms and maps room number to Room object.
 */
public class RoomManager implements IRoomManager {
  private final Map<Integer, IRoom> roomMap;

  /**
   * Constructor.
   */
  public RoomManager(Map<Integer, IRoom> roomMap) {
    this.roomMap = roomMap;
  }

  @Override
  public IRoom getRoomByNumber(int number) {
    return roomMap.get(number);
  }
}
