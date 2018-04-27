using System;
using System.Collections.Generic;
using System.Globalization;
using System.Threading;

namespace Partner.Data
{
    public static class Mock
    {
        // Liste de salle
        // Amphi Forum
        // E+130, E+131, E+132
        // O+101, O+107, O+108, O+109, O+110
        // O+302, O+303, O+307, O+308, O+309, O+310, O+311, O+317,

        public static List<Day> days = new List<Day>();

        public static List<Room> rooms = new List<Room>
        {
            new Room {
                ID = "Amphi Forum",
                Capacity = 300,
                Type = RoomType.Amphi
            }, // index 0
            new Room {
                ID = "E+130",
                Capacity = 100,
                Type = RoomType.Amphi
            }, // index 1
            new Room {
                ID = "E+131",
                Capacity = 100,
                Type = RoomType.Amphi
            }, // index 2
            new Room {
                ID = "E+132",
                Capacity = 100,
                Type = RoomType.Amphi
            }, // index 3

            new Room {
                ID = "O+101",
                Capacity = 50,
                Type = RoomType.Cours
            }, // index 4
            new Room {
                ID = "O+107",
                Capacity = 25,
                Type = RoomType.TD
            }, // index 5
            new Room {
                ID = "O+108",
                Capacity = 25,
                Type = RoomType.TD
            }, // index 6
            new Room {
                ID = "O+109",
                Capacity = 25,
                Type = RoomType.TD
            }, // index 7
            new Room {
                ID = "O+110",
                Capacity = 25,
                Type = RoomType.TD
            }, // index 8

            new Room {
                ID = "O+302",
                Capacity = 40,
                Type = RoomType.Cours
            }, // index 9
            new Room {
                ID = "O+303",
                Capacity = 40,
                Type = RoomType.Cours
            }, // index 10
            new Room {
                ID = "O+307",
                Capacity = 40,
                Type = RoomType.Cours
            }, // index 11
            new Room {
                ID = "O+308",
                Capacity = 45,
                Type = RoomType.Cours
            }, // index 12
            new Room {
                ID = "O+309",
                Capacity = 45,
                Type = RoomType.Cours
            }, // index 13
            new Room {
                ID = "O+310",
                Capacity = 45,
                Type = RoomType.Cours
            }, // index 14
            new Room {
                ID = "O+311",
                Capacity = 50,
                Type = RoomType.Cours
            }, // index 15
            new Room {
                ID = "O+317",
                Capacity = 50,
                Type = RoomType.Cours
            } // index 16
        };

        public static void MockDays()
        {
            DateTime dateTime = DateTime.Now;
            DayOfWeek day = dateTime.DayOfWeek;
            int difference = (7 + (dateTime.DayOfWeek - DayOfWeek.Monday)) % 7;
            dateTime = dateTime.AddDays(-difference);

            for (int i = 0; i < 14; ++i)
            {
                days.Add(new Day(dateTime));
                for (int j = 0; j < 9; ++j)
                {
                    string hour = j + 8 + ":00";
                    days[i].Planning[hour].AddRange(rooms);
                }
                dateTime = dateTime.AddDays(1);
            }

            // Monday
            for (int i = 0; i < 8; i += 7)
            {
                days[i].Planning["8:00"].RemoveAt(15);
                days[i].Planning["8:00"].RemoveAt(13);
                days[i].Planning["9:00"].RemoveAt(15);
                days[i].Planning["9:00"].RemoveAt(13);

                days[i].Planning["10:00"].RemoveAt(16);
                days[i].Planning["10:00"].RemoveAt(12);
                days[i].Planning["11:00"].RemoveAt(16);
                days[i].Planning["11:00"].RemoveAt(12);

                days[i].Planning["13:00"].RemoveAt(15);
                days[i].Planning["13:00"].RemoveAt(14);
                days[i].Planning["14:00"].RemoveAt(15);
                days[i].Planning["14:00"].RemoveAt(14);

                days[i].Planning["15:00"].RemoveAt(15);
                days[i].Planning["16:00"].RemoveAt(15);
            }

            // Tuesday
            for (int i = 1; i < 9; i += 7)
            {
                days[i].Planning["8:00"].RemoveAt(2);
                for (int j = 9; j < 16; j++)
                {
                    days[i].Planning[j + ":00"].RemoveAt(14);
                    days[i].Planning[j + ":00"].RemoveAt(8);
                    days[i].Planning[j + ":00"].RemoveAt(7);
                    days[i].Planning[j + ":00"].RemoveAt(6);

                    if (j == 11)
                    {
                        j++;
                    }
                }
            }

            // Wednesday
            for (int i = 2; i < 10; i += 7)
            {
                for (int j = 8; j < 12; j++)
                {
                    if (j >= 9)
                    {
                        days[i].Planning[j + ":00"].RemoveAt(15);
                        days[i].Planning[j + ":00"].RemoveAt(14);
                    }
                    else
                    {
                        days[i].Planning[j + ":00"].RemoveAt(15);
                        days[i].Planning[j + ":00"].RemoveAt(14);
                    }
                }

                days[i].Planning["13:00"].RemoveAt(0);
                days[i].Planning["14:00"].RemoveAt(0);

                days[i].Planning["15:00"].RemoveAt(16);
                days[i].Planning["15:00"].RemoveAt(11);
                days[i].Planning["16:00"].RemoveAt(16);
                days[i].Planning["16:00"].RemoveAt(11);
            }

            // Thursday
            for (int i = 3; i < 11; i += 7)
            {
                for (int j = 8; j < 17; j++)
                {
                    if (j >= 9)
                    {
                        days[i].Planning[j + ":00"].RemoveAt(16);
                        days[i].Planning[j + ":00"].RemoveAt(15);
                        days[i].Planning[j + ":00"].RemoveAt(14);
                        if (j == 11)
                        {
                            j++;
                        }
                    }
                    else if (j >= 13)
                    {
                        days[i].Planning[j + ":00"].RemoveAt(12);
                        days[i].Planning[j + ":00"].RemoveAt(11);
                    }
                    else
                    {
                        days[i].Planning[j + ":00"].RemoveAt(15);
                        days[i].Planning[j + ":00"].RemoveAt(14);
                    }
                }
            }

            // Friday
            for (int i = 4; i < 12; i += 7)
            {
                for (int j = 8; j < 17; j++)
                {
                    if (j < 12)
                    {
                        days[i].Planning[j + ":00"].RemoveAt(10);
                        days[i].Planning[j + ":00"].RemoveAt(9);
                        if (j == 11)
                        {
                            j++;
                        }
                    }
                    else
                    {
                        days[i].Planning[j + ":00"].RemoveAt(12);
                        days[i].Planning[j + ":00"].RemoveAt(11);
                    }
                    days[i].Planning[j + ":00"].RemoveAt(4);
                }
            }
        }

        public static bool UpdateRooms(string startDate, string endDate, List<string> roomIDs)
        {
            Thread.CurrentThread.CurrentCulture = new CultureInfo("fr-FR");
            DateTime startDateTime = DateTime.Parse(startDate);
            DateTime endDateTime = DateTime.Parse(endDate);

            List<Day> eventDays = new List<Day>();

            foreach (Day day in days)
            {
                if (startDateTime.ToShortDateString() == day.TheDay.ToShortDateString())
                {
                    eventDays.Add(day);
                }
                if (endDateTime.ToShortDateString() == day.TheDay.ToShortDateString())
                {
                    eventDays.Add(day);
                    break;
                }
                if (eventDays.Count != 0 && startDateTime.ToShortDateString() != day.TheDay.ToShortDateString())
                {
                    eventDays.Add(day);
                }
            }

            if (eventDays.Count == 1)
            {
                for (int i = startDateTime.Hour; i < endDateTime.Hour; ++i)
                {
                    if (!CheckRooms(roomIDs, eventDays[0], i))
                    {
                        return false;
                    }
                }
            }
            else
            {
                for (int i = 0; i < eventDays.Count; ++i)
                {
                    if (i == 0)
                    {
                        for (int j = startDateTime.Hour; j < 17; ++j)
                        {
                            if (!CheckRooms(roomIDs, eventDays[0], j))
                            {
                                return false;
                            }
                        }
                    }
                    else if (i == eventDays.Count - 1)
                    {
                        for (int j = 8; j < endDateTime.Hour; ++j)
                        {
                            if (!CheckRooms(roomIDs, eventDays[i], j))
                            {
                                return false;
                            }
                        }
                    }
                    else
                    {
                        for (int j = 8; j < 17; ++j)
                        {
                            if (!CheckRooms(roomIDs, eventDays[i], j))
                            {
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        }

        private static bool CheckRooms(List<string> roomIDs, Day day, int hour)
        {
            foreach (Room room in rooms)
            {
                if (roomIDs.Contains(room.ID))
                {
                    if (day.Planning[hour + ":00"].Contains(room))
                    {
                        day.Planning[hour + ":00"].Remove(room);
                    }
                    else
                    {
                        Console.WriteLine("Room:" + room.ID + " isn't vacant on " + day.TheDay.ToShortDateString() +
                            ".\nThe event couldn't be registered in the calendar.");
                        return false;
                    }
                }
            }

            return true;
        }
    }
}
