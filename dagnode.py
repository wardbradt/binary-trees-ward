# Ward Bradt
# 12/4/2017
class DagNode:
    def __init__(self, children=None):
        self.children = children if children is not None else []

    def add(self, weight, dag=None):
        dag = dag if dag is not None else DagNode()
        self.children.append({weight: dag})
        return dag

    def sum_children(self):
        # edge case
        if not self.children:
            return 0
        result = 0
        for it in self.children:
            (k, v), = it.items()
            result += k
        return result

    def __str__(self):
        return str(self.children)


class Dag:
    # nodes is an array of DagNodes that form a DAG.
    def __init__(self, nodes=None):
        self.nodes = nodes if nodes is not None else []
    
    def sum_weight(self):
        result = 0
        for n in self.nodes:
            result += n.sum_children()
        return result


# Use the given example
arr = []
for i in range(0, 11):
    arr.append(DagNode())

arr[0].add(2, dag=arr[1])
arr[1].add(2, dag=arr[2])
arr[2].add(5, dag=arr[3])
# arr[4] is the sink
arr[3].add(1, dag=arr[4])
arr[2].add(1, dag=arr[5])
arr[5].add(2, dag=arr[6])

arr[0].add(3, dag=arr[8])
arr[8].add(1, dag=arr[7])
arr[7].add(2, dag=arr[6])
arr[6].add(3, dag=arr[4])

arr[8].add(2, dag=arr[10])
arr[10].add(2, dag=arr[9])
arr[9].add(4, dag=arr[4])

d = Dag(arr)
print(d.sum_weight())
