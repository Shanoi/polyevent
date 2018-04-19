using System;
using System.Collections.Generic;

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

        private static List<Room> rooms = new List<Room>
        {
            new Room("Amphi Forum", 300, TypeSalle.Amphi), // index 0
            new Room("E+130", 100, TypeSalle.Amphi),       // index 1
            new Room("E+131", 100, TypeSalle.Amphi),       // index 2
            new Room("E+132", 100, TypeSalle.Amphi),       // index 3

            new Room("O+101", 50, TypeSalle.Cours),        // index 4
            new Room("O+107", 25, TypeSalle.TD),           // index 5
            new Room("O+108", 25, TypeSalle.TD),           // index 6
            new Room("O+109", 25, TypeSalle.TD),           // index 7
            new Room("O+110", 25, TypeSalle.TD),           // index 8

            new Room("O+302", 40, TypeSalle.Cours),        // index 9
            new Room("O+303", 40, TypeSalle.Cours),        // index 10
            new Room("O+307", 40, TypeSalle.Cours),        // index 11
            new Room("O+308", 45, TypeSalle.Cours),        // index 12
            new Room("O+309", 45, TypeSalle.Cours),        // index 13
            new Room("O+310", 45, TypeSalle.Cours),        // index 14
            new Room("O+311", 50, TypeSalle.Cours),        // index 15
            new Room("O+317", 50, TypeSalle.Cours)         // index 16
        };

        public static void MockDays()
        {
            DateTime dateTime = DateTime.Now;
            DayOfWeek day = dateTime.DayOfWeek;
            int difference = (7 + (dateTime.DayOfWeek - DayOfWeek.Monday)) % 7;
            dateTime.AddDays(-difference);

            for (int i = 0; i < 14; ++i)
            {
                days.Add(new Day(dateTime.ToString("d")));
                for (int j = 0; j < 10; ++j)
                {
                    string hour = j + 8 + ":00";
                    days[i].Planning[hour].AddRange(rooms);
                }
                dateTime.AddDays(1);
            }

            // Monday
            for (int i = 0; i < 8; i += 7)
            {
                days[i].Planning["8:00"].RemoveAt(15);
                days[i].Planning["8:00"].RemoveAt(13);
                days[i].Planning["9:00"].RemoveAt(15);
                days[i].Planning["9:00"].RemoveAt(13);

                days[i].Planning["10:00"].RemoveAt(12);
                days[i].Planning["10:00"].RemoveAt(16);
                days[i].Planning["11:00"].RemoveAt(12);
                days[i].Planning["11:00"].RemoveAt(16);

                days[i].Planning["13:00"].RemoveAt(14);
                days[i].Planning["13:00"].RemoveAt(15);
                days[i].Planning["14:00"].RemoveAt(14);
                days[i].Planning["14:00"].RemoveAt(15);

                days[i].Planning["15:00"].RemoveAt(15);
                days[i].Planning["16:00"].RemoveAt(15);
            }

            // Tuesday
            for (int i = 1; i < 9; i += 7)
            {
                days[i].Planning["8:00"].RemoveAt(2);
                for (int j = 9; j < 16; j++)
                {
                    days[i].Planning[j + ":00"].RemoveAt(6);
                    days[i].Planning[j + ":00"].RemoveAt(7);
                    days[i].Planning[j + ":00"].RemoveAt(8);
                    days[i].Planning[j + ":00"].RemoveAt(14);
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
                        days[i].Planning[j + ":00"].RemoveAt(14);
                        days[i].Planning[j + ":00"].RemoveAt(15);
                    }
                    else
                    {
                        days[i].Planning[j + ":00"].RemoveAt(14);
                        days[i].Planning[j + ":00"].RemoveAt(15);
                    }
                }
                
                days[i].Planning["13:00"].RemoveAt(0);
                days[i].Planning["14:00"].RemoveAt(0);

                days[i].Planning["15:00"].RemoveAt(11);
                days[i].Planning["15:00"].RemoveAt(16);
                days[i].Planning["16:00"].RemoveAt(11);
                days[i].Planning["16:00"].RemoveAt(16);
            }

            // Thursday
            for (int i = 3; i < 11; i += 7)
            {
                for (int j = 8; j < 17; j++)
                {
                    if (j >= 9)
                    {
                        days[i].Planning[j + ":00"].RemoveAt(14);
                        days[i].Planning[j + ":00"].RemoveAt(15);
                        days[i].Planning[j + ":00"].RemoveAt(16);
                        if (j == 11)
                        {
                            j++;
                        }
                    }
                    else if (j >= 13)
                    {
                        days[i].Planning[j + ":00"].RemoveAt(11);
                        days[i].Planning[j + ":00"].RemoveAt(12);
                    }
                    else
                    {
                        days[i].Planning[j + ":00"].RemoveAt(14);
                        days[i].Planning[j + ":00"].RemoveAt(15);
                    }
                }
            }

            // Friday
            for (int i = 4; i < 12; i += 7)
            {
                for (int j = 8; j < 17; j++)
                {
                    days[i].Planning[j + ":00"].RemoveAt(4);
                    if (j < 12)
                    {
                        days[i].Planning[j + ":00"].RemoveAt(9);
                        days[i].Planning[j + ":00"].RemoveAt(10);
                        if (j == 11)
                        {
                            j++;
                        }
                    }
                    else
                    {
                        days[i].Planning[j + ":00"].RemoveAt(11);
                        days[i].Planning[j + ":00"].RemoveAt(12);
                    }
                }
            }
        }
    }
}
