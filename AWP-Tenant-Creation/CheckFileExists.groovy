
def oldfile = new File('soutionyaml/solution.yml')
if(!oldfile.exists())
{
def src = new File('solution.yml')
def dest = new File('soutionyaml/solution.yml')
dest.write(src.text)
}




