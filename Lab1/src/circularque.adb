with Ada.Text_IO; use Ada.Text_IO;
with Food_DataStructures; use Food_DataStructures;

package body circularQue is
    package IntIO is new Ada.Text_IO.Integer_IO(Integer);
    use IntIO;

    subtype slotindex is natural range 1..(capacity - 1);  -- Natural implies >= 1.
    mesnum: Natural range 0..(capacity - 1) := 0;  -- Number of messages in the queue.
    box: array(slotindex) of Food_Pack;  -- Linear buffer.
    maxMessages: Natural := (capacity - 1);  -- Maximum number of messages.


    procedure insertInAscendingOrder(msg: in Food_Pack) is
    i: slotindex := mesnum - 1;
begin
    while i >= 0 and then Food_Pack_Compare(msg, box(i)) = -1 loop
        box(i + 1) := box(i);
        i := i - 1;
    end loop;
    box(i + 1) := msg;
    put("Position of Que: "); put(mesnum); New_Line;
    mesnum := mesnum + 1;
end insertInAscendingOrder;


    procedure acceptMessage(msg: in Food_Pack) is
    begin
        if mesnum < maxMessages then  -- reserve space and insert msg.
            insertInAscendingOrder(msg);
        else
            put("ERROR - Message rejected - queue is full!"); new_line(2);
        end if;
    end acceptMessage;

    procedure retrieveMessage(msg: out Food_Pack) is  -- ** modify in dual stacks to return highest priority food
    begin
        if mesnum > 0 then  -- remove message if buff not empty
            msg := box(1);
         for j in 1..(mesnum-1) loop
            box(j-1) := box(j);
            end loop;
            put("Position of Que"); put(mesnum); New_Line;
            mesnum := mesnum - 1;
        else
            put("ERROR - No message in the queue to retrieve!"); new_line(2);
        end if;
    end retrieveMessage;

    function circularQueEmpty return Boolean is
    begin
      if mesnum > 0 then
         return False;
      else
         return True;
      end if;
    end circularQueEmpty;

    function circularQueFull return Boolean is  -- ** modify for dual stacks
    begin
      if mesnum < maxMessages then
         return False;
      else
         return True;
      end if;
    end circularQueFull;

end circularQue;
