with Ada.Text_IO; use Ada.Text_IO;
with Food_DataStructures; use Food_DataStructures;
with Ada.Calendar;

generic
   type message is private;
   Capacity: Natural;

package CircularQue is


   procedure acceptMessage(msg: in Food_Pack);

   procedure retrieveMessage(msg: out Food_Pack);

   function circularQueEmpty return Boolean;

   function circularQueFull return Boolean;


   -- Add method (function or procedure) for inserting at the front of the queue here and in body.
end CircularQue;
