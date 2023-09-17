with Food_DataStructures;  use Food_DataStructures;
with Stats_FoodDistribution;  use Stats_FoodDistribution;
with GateKeeperService; use GateKeeperService;

with Ada.Text_IO;  use Ada.Text_IO;

package body Food_SalesService is

   package Integer_IO is new Ada.Text_IO.Integer_IO(Integer);
   use Integer_IO;

   task body RetailSales is
      food: Food_Pack;
      availableForSale: Boolean := true;
   begin
     delay 1.0;  -- Allow for initialization activities.
     loop
        GateKeeper.retrieveMessage( food, availableForSale );

        -- The time to sell a product is exponentially distributed with mean 2.0 hours.
        delay( duration( Next_Exponential * 2.0 ) );

        put("Retail Sales successfuly sold "); PrintFood_Pack( food ); new_line;

      end loop;
  end RetailSales;

end Food_SalesService;
