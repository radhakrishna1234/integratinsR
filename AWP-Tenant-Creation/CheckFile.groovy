String fileContents = new File('soutionyaml/solution.yml').text
boolean flag = fileContents.contains("organizations:");

if(flag)
{
def file1 = new File('solution.yml')
file1.delete()



def newfile = new File('solution.yml')
def oldfile = new File('soutionyaml/solution.yml')

def src = new File('soutionyaml/solution.yml')
def dest = new File('solution.yml')
dest.write(src.text)

} 