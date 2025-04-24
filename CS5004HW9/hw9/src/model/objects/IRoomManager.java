package model.objects;

/**
 * Create a RoomManager interface.
 * RoomManager contains all rooms and maps room number to Room object.
 */
public interface IRoomManager {
  /**
   * get room object by room number.
   * @param number number of the room.
   * @return room object.
   */
  IRoom getRoomByNumber(int number);

}
