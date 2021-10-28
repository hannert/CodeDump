import time
def backTrack(workingSet, counter, input): 
    c = []
    nc = 0;

    if(isSolution(workingSet)):
        processSolution(workingSet)
    else:
        counter += 1
        for x in range(nc):
            workingSet.append(x)
            backTrack(workingSet, counter, input)
            if finished:
                return


def constructCandidates(workingSet, counter, input, c, nc):
    inPerm = [False for x in subSize] # Initialize a false for each element 
    for 


def processSolution(solution):
    finished = True
    for sets in solution:
        print(sets)

def isSolution(superset):
    answer = [range(1, maxSize+1)]
    for number in answer:
        if number not in superset:
            return False
    return True

if __name__ == "__main__":
    startTime = time.time()


    file1 = open('s-X-12-6.txt', 'r')
    Lines = file1.readlines()
    count = 0
    finished = False
    maxSize = 0
    subSize = 0
    mySets = []
    for line in Lines:
        count += 1
        if count == 1:
            maxSize = int(line)
        elif count == 2:
            subSize = int(line)
        else:
            temp = line.strip("\n")
            tempList = temp.split(" ")
            numList = [int(i) for i in tempList]                
            mySets.append(numList)


    print("Size of universal set: " + str(maxSize))
    print("Number of subsets: " + str(subSize))
    for sets in mySets:
        print(sets)
    
    workingSet = []
    backtrack(workingSet, 0, mySets)
    print("Finished in " + str(round((time.time() - startTime) * 1000)) + "ms")
