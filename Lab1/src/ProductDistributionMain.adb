
with Ada.Text_IO; use Ada.Text_IO;

with Food_DataStructures;  use Food_DataStructures;

with Stats_FoodDistribution;  use Stats_FoodDistribution;

with Distribution_Service; use Distribution_Service;

with Food_SalesService;  use Food_SalesService;

with GateKeeperService;  use GateKeeperService;


procedure ProductDistributionMain is

   package INt_IO is new Ada.Text_IO.Integer_IO(Integer); use Int_IO;

   numProductGenerators: Positive := 1; -- number product generators.
   numPOS: Positive := 2;               -- number points of sale.

   --SalesPerson: RetailSales; -- single sales center.


begin --body ProductDistributionMain

   put("How many Product Generators?  "); get(numProductGenerators);  -- number receiving stations.
   new_line;
   put("How many points of sale?  "); get(numPOS);  -- number receiving stations.
   new_line(2);


   declare
         FarmProducts: array(1..numProductGenerators) of Product_Generator;
         POS: array(1..numPOS) of RetailSales;
   begin
      null;
   end;

end ProductDistributionMain;
