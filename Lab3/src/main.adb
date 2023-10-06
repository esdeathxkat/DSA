with Ada.Text_IO; use Ada.Text_IO;

procedure Main is
    type JobType is (Accountant, Analyst, Manager, Manufacturing, Programmer,
                     Inventory, Sales, SoftwareEngineer);
    package JobTypeIO is new Ada.Text_IO.Enumeration_IO(JobType); use JobTypeIO;

    type EmpName is (Ben, Betty, Bob, Damon, Darlene, David, Desire, Donald, Dustin,
                     Jerry, Kevin, Mary, Marty, Sable, Sam, Sara, Teddy, Tom);
    package EmpNameIO is new Ada.Text_IO.Enumeration_IO(EmpName); use EmpNameIO;

    type LegalResponse is (yup, y, yes, affirmative, nope, no, n, negative);
    subtype PositiveResponse is LegalResponse range yup..affirmative;
    package LegalIO is new Ada.Text_IO.Enumeration_IO(LegalResponse); use LegalIO;

    package IntIO is new Ada.Text_IO.Integer_IO(Integer); use IntIO;

    type Emp is record
        Name: EmpName;
        Job:  JobType;
        Age: Integer;
        Next: Integer; -- Pointer to the next employee in the linked list
    end record;

    function "<" (Left, Right: Emp) return Boolean is
    begin
     -- First, compare job types
        if Left.Job /= Right.Job then
            return Left.Job < Right.Job;
        end if;

        -- If job types are the same, compare ages
        if Left.Age /= Right.Age then
            return Left.Age < Right.Age;
        end if;

        -- If ages are the same, compare names
        return Left.Name < Right.Name;
   end "<";


    procedure Swap(Left, Right: in out Emp) is
    Temp: Emp;
    begin
     Temp := Left;
     Left := Right;
     Right := Temp;
    end Swap;



    SortByJob: array(JobType) of Integer := (others => 0);

    SortSpace: array(1..100) of Emp;
    Avail: Integer := 1; -- Dynamic storage allocator
    Pt, PrevPt: Integer;

    Again: LegalResponse := affirmative;

    --InputFile : File_Type;
begin

    --Ada.Text_IO.Open(InputFile, Ada.Text_IO.In_File, "LabCinfo.txt");

    -- Loop to read data from the file
    while (Again in PositiveResponse) loop
        put("Enter Name: "); get(SortSpace(Avail).Name); -- Get emp info.
        put("Enter Job type: "); get(SortSpace(Avail).Job);
        put("Enter Age: "); get(SortSpace(Avail).Age);

        -- Insert in appropriate list (by job type, age, and name).
        Pt := SortByJob(SortSpace(Avail).Job);
        PrevPt := 0;

        while Pt /= 0 and then SortSpace(Pt) < SortSpace(Avail) loop
            PrevPt := Pt;
            Pt := SortSpace(Pt).Next;
        end loop;
            -- Finds the correct position to insert.
        SortSpace(Avail).Next := Pt;

        if PrevPt = 0 then
            -- Insert at the beginning of the list.
            SortByJob(SortSpace(Avail).Job) := Avail;
        else
            -- Insert in the middle or at the end of the list.
            SortSpace(PrevPt).Next := Avail;
        end if;

        -- Prepare for the next dynamically allocated node.
        Avail := Avail + 1;

        put("Enter another name (yup or nope): "); get(Again);
    end loop;


    -- Close the input file
    --Ada.Text_IO.Close(InputFile);

   -- Sorting logic here (using Next pointers)

    -- Output sorted list
    for I in JobType loop -- Traverse.
        new_line; put("Job Type = "); put (I); new_line;
        Pt := SortByJob(I);  -- Point to the first node in the job list.
        while Pt /= 0 loop
            put("Name:  "); put(SortSpace(Pt).Name); put("| Job Type:  "); put(SortSpace(Pt).Job); put("| Age:  "); put(SortSpace(Pt).Age);
            put("  link = "); put(SortSpace(Pt).Next,4); new_line;
            Pt := SortSpace(Pt).Next;  -- Move to the next node.
        end loop;
    end loop;
end Main;
