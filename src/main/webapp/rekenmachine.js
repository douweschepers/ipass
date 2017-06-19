Memory  = "0";      // initialise memory variable
Current = "0";      //   and value of Display ("current" value)
Operation = 0;      // Records code for eg * / etc.


function addDigit(dig) {
	//console.log(dig);
	//Current +=-1;
	//Current += dig;
	//document.querySelector('#display').innerHTML = Current;	

	if (    (eval(Current) == 0)
            && (Current.indexOf(".") == -1)
          )
         { Current = dig;
         } else
         { Current = Current + dig;
         };
         document.querySelector('#display').innerHTML = Current;
  }; 
 
	
	
 function Operate(op)            //STORE OPERATION e.g. + * / etc.
  {
	   if (op.indexOf("*") > -1) { Operation = 1; };       //codes for *
	   if (op.indexOf("/") > -1) { Operation = 2; };       // slash (divide)
	   if (op.indexOf("+") > -1) { Operation = 3; };       // sum
	   if (op.indexOf("-") > -1) { Operation = 4; };       // difference
	
	   Memory = Current;                 //store value
	   Current = "";                     //or we could use "0"
	   document.querySelector('#display').innerHTML = Current;
  }
function uitrekenen()            //PERFORM CALCULATION (= button)
{ 
	 if (Operation == 1) { Current = eval(Memory) * eval(Current); };
	 if (Operation == 2) { Current = eval(Memory) / eval(Current); };
	 if (Operation == 3) { Current = eval(Memory) + eval(Current); };
	 if (Operation == 4) { Current = eval(Memory) - eval(Current); };
	 Operation = 0;                //clear operation
	 Memory    = "0";              //clear memory
	 document.querySelector('#display').innerHTML = Current;
}


function AllClear()             //Clear ALL entries!
	{ Current = "0";
	  Operation = 0;                //clear operation
	  Memory = "0";                  //clear memory
	  document.querySelector('#display').innerHTML = Current;
}

