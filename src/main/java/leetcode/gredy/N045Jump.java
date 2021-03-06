package leetcode.gredy;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * 说明:
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * 来源：力扣（LeetCode）   45
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class N045Jump {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new N045Jump().jump(nums));
    }
    /*
    对于[2,3,1,1,4]来说，我们会怎么思考。首先index=0，我们可以跳1步来到index=1，也可以跳2步到达index=2.
    因此跳一次我们能到达的最远的位置为index=2.
    然后我们从index=1出发，最多可以跳3步，可以来到index=2，3，4. 此时已经到达末尾。流程结束。

    假设是[2,2,3,1,4]呢？还是一样。首先index=0，我们可以跳1步来到index=1，也可以跳2步到达index=2.
    因此跳一次我们能到达的最远的位置为index=2.
    然后我们从index=1出发，最多可以跳2步，可以来到index=2，3。我们从index=2出发的话，最远跳3步，可以到达位置3，4，5.

    总结一下：解决这个问题，我们需要知道上次能到达的位置，作为这一次的起点。然后需要知道此次能到达的最远的位置，作为下一次的起点

    [2,       3,1,       1,2,2,1]
    [2,3,     1,1,2,      2,1]
    [2,3,1,1,2,   2,1        ]

    将数组分成三段，最左边，已经到达的位置。中间，此次可以到达的位置。右边，本次不能到达的位置。
    对于数组[2,       3,1,       1,2,2,1]。第一步可以到达index=1或者index=2.但是如果调到index=1的话显然下次可以跳的更远。因此第一步跳到index=1的位置。
    变成[2,3,     1,1,2,      2,1]。第二次可以到达index=2，3，4。显然跳到index=4的话，下次可以跳的更远。
    此时变成[2,3,1,1,2,   2,1        ]。此时一次跳2步到达最右端。
    总结一下规律，我们在跳的时候会考虑到下一步。根据下一步能跳的最远的贪心策略，来进行每一步的选择。

    用变量right表示本次能到达的最远的位置。用变量maxPos表示下次能到达的最远的位置。
    则在本位置到right此轮循环中maxPos的变化规律为max(maxPos,nums[i] + i);



     */

    public int jump(int[] nums) {
        int cnt = 0;
        if (nums == null || nums.length == 0) {
            return cnt;
        }
        int len = nums.length;
        int maxPos = 0; //下次最远能到达的位置
        int right = 0;   //本次次最远能到达的位置

        for (int i = 0; i < len - 1; i++) {
            maxPos = (nums[i] + i) > maxPos ? (nums[i] + i) : maxPos;
            if (i == right) {
                cnt++;
                right = maxPos;
            }

        }

        return cnt;

    }
}
